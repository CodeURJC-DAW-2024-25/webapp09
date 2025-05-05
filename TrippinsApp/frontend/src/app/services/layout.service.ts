import { Injectable } from '@angular/core';
import $ from 'jquery';

@Injectable({
  providedIn: 'root'
})
export class LayoutService {
  initializeDropdownHover() {
    const $dropdown = $(".dropdown");
    const $dropdownToggle = $(".dropdown-toggle");
    const $dropdownMenu = $(".dropdown-menu");
    const showClass = "show";

    $(window).on("load resize", () => {
      if (window.matchMedia("(min-width: 992px)").matches) {
        $dropdown.hover(
          function () {
            const $this = $(this);
            $this.addClass(showClass);
            $this.find($dropdownToggle).attr("aria-expanded", "true");
            $this.find($dropdownMenu).addClass(showClass);
          },
          function () {
            const $this = $(this);
            $this.removeClass(showClass);
            $this.find($dropdownToggle).attr("aria-expanded", "false");
            $this.find($dropdownMenu).removeClass(showClass);
          }
        );
      } else {
        $dropdown.off("mouseenter mouseleave");
      }
    });
  }

  initializeVideoModal() {
    $(document).ready(() => {
      let $videoSrc: string;  
      $('.btn-play').on('click', function () {
        $videoSrc = $(this).data("src");
      });

      $('#videoModal').on('shown.bs.modal', function () {
        $("#video").attr('src', $videoSrc + "?autoplay=1&amp;modestbranding=1&amp;showinfo=0");
      });

      $('#videoModal').on('hide.bs.modal', function () {
        $("#video").attr('src', $videoSrc);
      });
    });
  }

  checkLoggedInCookie() {
    if (document.cookie.includes('justLoggedIn=true')) {
      document.cookie = 'justLoggedIn=; Path=/; Expires=Thu, 01 Jan 1970 00:00:01 GMT;';
      setTimeout(() => {
        window.location.reload();
      }, 50);
    }
  }
}