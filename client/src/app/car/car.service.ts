import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable({
  providedIn: 'root'
})
export class CarService {
  public API = 'http://localhost:8080';
  public CAR_API = this.API + '/car';

  
  constructor(private http: HttpClient) {
  }

  getAll(): Observable<any> {
    return this.http.get(this.CAR_API);
  }

  get(id: string) {
	  return this.http.get(this.CAR_API + '?VIN=' + id);
  }

  save(car: any): Observable<any> {
    let result: Observable<Object>;
    if (car['href']) {
      result = this.http.put(this.CAR_API, car);
    } else {
      result = this.http.post(this.CAR_API, car);
    }
    return result;
  }

  remove(car: any) {
    return this.http.delete(this.CAR_API, car);
  }

}
