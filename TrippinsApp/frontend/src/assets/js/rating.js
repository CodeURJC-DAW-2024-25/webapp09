document.addEventListener("DOMContentLoaded", function() {
    const ratingFills = document.querySelectorAll('.rating-fill');

    ratingFills.forEach(ratingFill => {
        const rating = parseInt(ratingFill.style.width, 10);

        let red, green, blue;
        if (rating <= 50) {
            red = 255;
            green = Math.round((rating / 50) * 255);
            blue = 0;
        } else {
            red = Math.round(((100 - rating) / 50) * 255);
            green = 255;
            blue = 0;
        }

        ratingFill.style.backgroundColor = `rgb(${red}, ${green}, ${blue})`;
    });
});

