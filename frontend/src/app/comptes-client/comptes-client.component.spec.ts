import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ComptesClientComponent } from './comptes-client.component';

describe('ComptesClientComponent', () => {
  let component: ComptesClientComponent;
  let fixture: ComponentFixture<ComptesClientComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ComptesClientComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ComptesClientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
