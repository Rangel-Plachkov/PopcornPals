import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActorUpdateComponent } from './actor-update.component';

describe('ActorUpdateComponent', () => {
  let component: ActorUpdateComponent;
  let fixture: ComponentFixture<ActorUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ActorUpdateComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ActorUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
