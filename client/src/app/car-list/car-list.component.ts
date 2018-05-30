import { CarService } from '../car/car.service';
import { GiphyService } from '../giphy/giphy.service'
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-car-list',
  templateUrl: './car-list.component.html',
  styleUrls: ['./car-list.component.css']
})
export class CarListComponent implements OnInit {

  cars: Array<any>;
  constructor(private carService: CarService, private giphyService: GiphyService) { }

  ngOnInit() {
	  this.carService.getAll().subscribe(data => {
      this.cars = data;
	  for (const car of this.cars) {
        this.giphyService.get(car.name).subscribe(url => car.giphyUrl = url);
      }

    });

  }

}
