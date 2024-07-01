/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { MediaListComponent } from './media-list.component';

describe('MediaListComponent', () => {
  let component: MediaListComponent;
  let fixture: ComponentFixture<MediaListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MediaListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MediaListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
