import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModificaesameComponent } from './modificaesame.component';

describe('ModificaesameComponent', () => {
  let component: ModificaesameComponent;
  let fixture: ComponentFixture<ModificaesameComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModificaesameComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModificaesameComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
