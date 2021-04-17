import { Component, OnInit } from '@angular/core';
import { Purchase } from 'src/app/models/purchase';
import { PurchaseService } from 'src/app/services/purchase.service';

@Component({
  selector: 'app-purchase-list',
  templateUrl: './purchase-list.component.html',
  styleUrls: ['./purchase-list.component.css']
})
export class PurchaseListComponent implements OnInit {

  purchases: Purchase[] = [];
  //selected
  //newpurchase

  constructor(
    private PurchaseService: PurchaseService
  ) { }

  ngOnInit(): void {

    this.loadPurchases();

  }

  loadPurchases(): void {
    this.PurchaseService.index().subscribe(
      purchases => {
        this.purchases=purchases;
      },
      fail => {
        console.error('PurchaseListComponent.loadPurchases(): ');
        console.error(fail);
      }
    );
  }

}
