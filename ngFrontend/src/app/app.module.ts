import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PurchaseService } from './services/purchase.service';
import { PurchaseListComponent } from './components/purchase-list/purchase-list.component';

@NgModule({
  declarations: [
    AppComponent,
    PurchaseListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [
    PurchaseService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
