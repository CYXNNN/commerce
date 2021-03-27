const fs = require('fs');
const path = require('path');
const childProcess = require('child_process');

function deleteFile(filePath) {
  fs.unlinkSync(filePath);
}

function deleteOldFiles(directory, timestamp) {
  if (fs.existsSync(directory)) {
    let files = fs.readdirSync(directory);
    for (const file of files) {
      const filePath = path.join(directory, file);
      const stats = fs.statSync(filePath);
      if (stats.mtime.getTime() < timestamp.getTime()) {
        deleteFile(filePath);
      }
    }
  }
}

async function generateOpenApi(directory) {
  const env = {
    ...process.env,
    JAVA_OPTS: ''
  };
  const cmd = 'npx @openapitools/openapi-generator-cli generate -i http://localhost:8080/commerce/openapi.yml -g typescript-angular'
    + ' -p stringEnums=true'
    + ' -p modelSuffix=TS'
    + ' -p fileNaming=kebab-case'
    + ' --type-mappings DateTime=Date,date=Date,Date=Date,AnyType=object'
    + ' -o ' + directory
  console.log('executing command: ', cmd);
  const child = childProcess.exec(
    cmd,
    {env}
  );
  child.stdout.on('data', function (data) {
    console.log(data.toString());
  });
  child.stderr.on('data', function (data) {
    console.error(data.toString());
  });

  return new Promise(
    (resolve, reject) => {
      child.on('exit',
        code => code === 0 ? resolve() : reject("exit code: " + code));
      child.on('error', reject);
    }
  );
}

async function generateIndexTs(directory) {
  // read dir and sort it, otherwise different OS/Locales handle it case in/-sensitive
  let files = fs.readdirSync(directory)
  .sort((a, b) => a.toLocaleLowerCase().localeCompare(b.toLocaleLowerCase()));
  let writer = fs.createWriteStream(path.join(directory, 'index.ts'));
  for (const file of files.filter(f => f !== 'index.ts')) {
    writer.write('export * from \'./' + path.basename(file, '.ts') + '\'\n');
  }
  writer.close();

  return new Promise(resolve => writer.on('close', resolve));
}

async function sleep(msec) {
  return new Promise((resolve) => setTimeout(resolve, msec));
}

// async main
(async () => {

  const timestamp = new Date();
  // noinspection MagicNumberJS
  await sleep(100); // make sure timestamp ticks

  const generatorPath = 'src/';
  const modelsPath = path.join(generatorPath, 'model');

  await generateOpenApi(generatorPath)
  .then(_ => generateIndexTs(modelsPath))
  .catch(err => console.error('Error generating models', err));

  deleteOldFiles(modelsPath, timestamp);

})();
