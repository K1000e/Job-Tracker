import { Component, OnInit } from '@angular/core';
import { CompanyService } from './services/company.service';
import { Company } from './models/company';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {

  companies: Company[] = [];

  constructor(
    private companyService: CompanyService
  ) {}

  ngOnInit(): void {
    this.loadCompanies();
  }


  loadCompanies(): void {
    this.companyService
      .getCompanies()
      .subscribe(companies => {
        this.companies = companies;
      });
  }
}
