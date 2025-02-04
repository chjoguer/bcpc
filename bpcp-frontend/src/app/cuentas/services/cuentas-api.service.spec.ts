import { TestBed } from '@angular/core/testing';

import { CuentasApiService } from './cuentas-api.service';

describe('CuentasApiService', () => {
  let service: CuentasApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CuentasApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
