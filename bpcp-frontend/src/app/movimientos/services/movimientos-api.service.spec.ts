import { TestBed } from '@angular/core/testing';

import { MovimientosApiService } from './movimientos-api.service';

describe('MovimientosApiService', () => {
  let service: MovimientosApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MovimientosApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
