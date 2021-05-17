import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

const AUTH_API = 'http://localhost:8080/commerce/user/v1';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

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
    }, httpOptions);
  }

  register(user): Observable<any> {
    return this.http.post(AUTH_API + '/register', {
      username: user.username,
      email: user.email,
      hash: user.password
    }, httpOptions);
  }

  tokenvalidity(authToken: string, authId: string): Observable<boolean> {
    return this.http.get<boolean>(AUTH_API + '/tokenvalidity/' + authToken + '/' + authId);
  }
}
