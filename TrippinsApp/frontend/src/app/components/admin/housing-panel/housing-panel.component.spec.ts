import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HousingPanelComponent } from './housing-panel.component';

describe('HousingPanelComponent', () => {
  let component: HousingPanelComponent;
  let fixture: ComponentFixture<HousingPanelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [HousingPanelComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(HousingPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
