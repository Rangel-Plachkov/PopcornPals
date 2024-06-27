import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActorMediaComponent } from './actor-media.component';

describe('ActorMediaComponent', () => {
  let component: ActorMediaComponent;
  let fixture: ComponentFixture<ActorMediaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ActorMediaComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ActorMediaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
