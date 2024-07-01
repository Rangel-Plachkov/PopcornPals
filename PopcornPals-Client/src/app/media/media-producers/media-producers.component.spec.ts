/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { MediaProducersComponent } from './media-producers.component';

describe('MediaProducersComponent', () => {
  let component: MediaProducersComponent;
  let fixture: ComponentFixture<MediaProducersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MediaProducersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MediaProducersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
