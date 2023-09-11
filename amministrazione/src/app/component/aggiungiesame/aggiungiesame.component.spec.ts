import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AggiungiesameComponent } from './aggiungiesame.component';

describe('AggiungiesameComponent', () => {
  let component: AggiungiesameComponent;
  let fixture: ComponentFixture<AggiungiesameComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AggiungiesameComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AggiungiesameComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
