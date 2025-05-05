import { Component, OnInit } from '@angular/core';
import { LayoutService } from './services/layout.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
  standalone:false
})
export class AppComponent implements OnInit{
  title = 'TrippinsApp';

  constructor(private layoutService: LayoutService){

  }

  ngOnInit(){
    
    this.layoutService.initializeDropdownHover();
    this.layoutService.initializeVideoModal();
    this.layoutService.checkLoggedInCookie();

  }
}
