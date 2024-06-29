/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { StudioService } from './studio.service';

describe('Service: Studio', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [StudioService]
    });
  });

  it('should ...', inject([StudioService], (service: StudioService) => {
    expect(service).toBeTruthy();
  }));
});
