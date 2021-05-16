import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {AppSettings} from "../app.settings";

@Injectable()
export class ProvisioningService {

  constructor(private httpClient: HttpClient) {
  }

  public get(url: string): Observable<any> {
    const headers = new HttpHeaders()
    .append('Authorization', 'Basic ' + 'YWRtaW46cGFzcw==')

    const options = {headers: headers};
    return this.httpClient.get(url, options);
  }

  public getWeaponRules(): Observable<Map<string, string>> {
    const headers = new HttpHeaders()
    .append('Authorization', 'Basic ' + 'YWRtaW46cGFzcw==')

    const options = {headers: headers};
    return this.httpClient.get<Map<string, string>>(AppSettings.WEAPON_RULES, options);
  }

  public post(url: string, body: any): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Basic TXlrb2xhLk1hdHNpc2hpbkBhdmlzcGwuY29tOjEyMw=='
    });
    const options = {headers: headers};
    return this.httpClient.post(url, body, options);
  }

  // public post(url: string, file: File): Observable<number> {
  //
  //   var subject = new Subject<number>()
  //   const req = new HttpRequest('POST', url, file, {
  //     reportProgress: true,
  //   });
  //
  //   this.httpClient.request(req).subscribe(event => {
  //     if (event.type === HttpEventType.UploadProgress) {
  //       const percentDone = Math.round(100 * event.loaded / event.total);
  //       subject.next(percentDone);
  //     } else if (event instanceof HttpResponse) {
  //       subject.complete();
  //     }
  //   });
  //   return subject.asObservable();
  // }
}

