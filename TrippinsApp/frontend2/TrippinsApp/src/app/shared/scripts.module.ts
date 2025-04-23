import { NgModule, Injectable, APP_INITIALIZER } from '@angular/core';
import $ from 'jquery';

declare global {
  interface Window {
    WOW: any;
  }
}

@Injectable({ providedIn: 'root' })
export class ScriptInitializerService {
  initialize(): Promise<void> {
    return new Promise((resolve) => {
      // Spinner
      setTimeout(() => {
        if ($('#spinner').length > 0) {
          $('#spinner').removeClass('show');
        }
      }, 1);

      // Initialize Wow.js
      if (typeof window.WOW === 'function') {
        new window.WOW().init();
      }

      // Back to top button
      $(window).scroll(() => {
        if ($(window).scrollTop()! > 300) {
          $('.back-to-top').fadeIn('slow');
        } else {
          $('.back-to-top').fadeOut('slow');
        }
      });

      $('.back-to-top').on('click', () => {
        $('html, body').animate({ scrollTop: 0 }, 1500, 'easeInOutExpo');
        return false;
      });

      // Counter Up
      ($('[data-toggle="counter-up"]') as any).counterUp({
        delay: 10,
        time: 2000
      });

      // Testimonials carousel
      $(".testimonial-carousel").owlCarousel({
        autoplay: true,
        smartSpeed: 1000,
        margin: 25,
        dots: false,
        loop: true,
        nav: true,
        navText: [
          '<i class="bi bi-arrow-left"></i>',
          '<i class="bi bi-arrow-right"></i>'
        ],
        responsive: {
          0: { items: 1 },
          768: { items: 2 }
        }
      });

      resolve();
    });
  }
}

export function scriptInitializerFactory(provider: ScriptInitializerService) {
  return () => provider.initialize();
}

@NgModule({
  providers: [
    ScriptInitializerService,
    {
      provide: APP_INITIALIZER,
      useFactory: scriptInitializerFactory,
      deps: [ScriptInitializerService],
      multi: true
    }
  ]
})
export class ScriptsModule {}