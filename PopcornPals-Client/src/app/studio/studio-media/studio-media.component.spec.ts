/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { StudioMediaComponent } from './studio-media.component';

describe('StudioMediaComponent', () => {
  let component: StudioMediaComponent;
  let fixture: ComponentFixture<StudioMediaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StudioMediaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StudioMediaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
