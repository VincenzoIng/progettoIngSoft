import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SvolgiesameComponent } from './svolgiesame.component';

describe('SvolgiesameComponent', () => {
  let component: SvolgiesameComponent;
  let fixture: ComponentFixture<SvolgiesameComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SvolgiesameComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SvolgiesameComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
