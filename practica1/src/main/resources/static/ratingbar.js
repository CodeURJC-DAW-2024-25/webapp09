document.addEventListener("DOMContentLoaded", function() {
    const ratingFills = document.querySelectorAll('.rating-fill');

    ratingFills.forEach(ratingFill => {
        const rating = parseInt(ratingFill.style.width, 10);

        // Calcular el color basado en el rating
        const red = 255 - (rating * 255 / 100);
        const green = rating * 255 / 100;
        const blue = 0;

        ratingFill.style.backgroundColor = `rgb(${red}, ${green}, ${blue})`;
    });
});