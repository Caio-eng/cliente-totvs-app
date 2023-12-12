import { Component, HostListener, OnInit } from '@angular/core';
import {  MatSnackBarConfig } from '@angular/material/snack-bar';
import { drawerAnimation } from 'src/app/animations/drawer.animation';
import { LoadingService } from 'src/app/services/loading.service';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css'],
  animations: [drawerAnimation],
})

export class NavComponent implements OnInit {
  isWideScreen: boolean = true;
  isDrawerOpen = false;

  toggleDrawer() {
    this.isDrawerOpen = !this.isDrawerOpen;
  }

  closeDrawer() {
    this.isDrawerOpen = false;
  }

  config: MatSnackBarConfig = {
    duration: 7000
  };

  @HostListener('window:resize', ['$event'])
  onResize() {
    this.checkScreenWidth();
  }

  private checkScreenWidth() {
    this.isWideScreen = window.innerWidth >= 325;
  }


  constructor(
    public loadingService: LoadingService,) { }

  ngOnInit(): void {
  }
}
