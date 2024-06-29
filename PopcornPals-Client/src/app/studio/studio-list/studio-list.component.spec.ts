/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { StudioListComponent } from './studio-list.component';

describe('StudioListComponent', () => {
  let component: StudioListComponent;
  let fixture: ComponentFixture<StudioListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StudioListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StudioListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
