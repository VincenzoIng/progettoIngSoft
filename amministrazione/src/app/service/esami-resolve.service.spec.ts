import { TestBed } from '@angular/core/testing';

import { EsamiResolveService } from './esami-resolve.service';

describe('EsamiResolveService', () => {
  let service: EsamiResolveService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EsamiResolveService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
