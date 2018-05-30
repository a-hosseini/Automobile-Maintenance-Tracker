import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { ActivatedRoute, Router } from '@angular/router';
import { CarService } from '../car/car.service';
import { GiphyService } from '../giphy/giphy.service'
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-car-edit',
  templateUrl: './car-edit.component.html',
  styleUrls: ['./car-edit.component.css']
})
export class CarEditComponent implements OnInit, OnDestroy {
  car: any = {};

  sub: Subscription;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private carService: CarService,
              private giphyService: GiphyService) {
  }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
	  const id = params['id'];
      if (id) {
		  this.carService.get(id).subscribe((car: any) => {
          if (car) {
            this.car = car[0];
			this.car.href = car[0]._links.self.href;
			this.giphyService.get(this.car.vin).subscribe(url => this.car.giphyUrl = url);
          } else {
            console.log(`Car with id '${id}' not found, returning to list`);
            this.gotoList();
          }
        });
      }
    });
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  gotoList() {
    this.router.navigate(['/car-list']);
  }

  save(form: NgForm) {
    this.carService.save(form).subscribe(result => {
      this.gotoList();
    }, error => console.error(error));
  }

  remove(car: any) {
    this.carService.remove(car).subscribe(result => {
      this.gotoList();
    }, error => console.error(error));
  }
}
