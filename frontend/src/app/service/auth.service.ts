import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

const AUTH_API = 'http://cyxn.fans:8080/commerce/user/v1';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) {
  }

  login(credentials): Observable<any> {
    return this.http.post(AUTH_API, {
      username: credentials.username,
      hash: credentials.password
    });
  }

  register(user): Observable<any> {
    return this.http.post(AUTH_API + '/register', {
      username: user.username,
      email: user.email,
      hash: user.password
    });
  }

  tokenvalidity(authToken: string, authId: string): Observable<boolean> {
    return this.http.get<boolean>(AUTH_API + '/tokenvalidity/' + authToken + '/' + authId);
  }

  logout(): void {
    this.http.get(AUTH_API + '/logout').subscribe();
  }
}
