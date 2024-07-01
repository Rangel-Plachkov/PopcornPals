/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { MediaActorsComponent } from './media-actors.component';

describe('MediaActorsComponent', () => {
  let component: MediaActorsComponent;
  let fixture: ComponentFixture<MediaActorsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MediaActorsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MediaActorsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
