let currentReviewPage = 0; // Track the current page for reviews
const reviewPageSize = 3; // Number of reviews to load per request

// Add event listener to the "Load More Reviews" button
document.getElementById('load-more-reviews').addEventListener('click', async function () {
    try {
        currentReviewPage++; // Increment the page number
        const reviews = await fetchReviews(currentReviewPage, reviewPageSize); // Fetch reviews
        appendReviews(reviews); // Append reviews to the container

        // Disable the "Load More Reviews" button if there are no more reviews
        if (reviews.length < reviewPageSize) {
            document.getElementById('load-more-reviews').disabled = true;
        }
    } catch (error) {
        console.error('Error fetching reviews:', error);
    }
});

// Function to fetch reviews from the server
async function fetchReviews(page, size) {
    try {
        const response = await fetch('/reviews/paginated', {
            method: 'POST', // Use POST 
            headers: {
                'Content-Type': 'application/json', // Specify the content type as JSON
            },
            body: JSON.stringify({ page, size }), // Send page and size in the request body
        });

        if (!response.ok) {
            const errorText = await response.text();
            console.error("Server response:", errorText);
            throw new Error(`Failed to fetch reviews: ${errorText}`);
        }

        const data = await response.json();
        return data.content; // Return the list of reviews
    } catch (error) {
        console.error('Error fetching reviews:', error);
        throw error; // Re-throw the error to handle it in the calling function
    }
}

// Function to append reviews to the container
function appendReviews(reviews) {
    const reviewsContainer = document.getElementById('reviews-container');
    reviews.forEach(review => {
        const reviewHtml = `
            <div class="review-item shadow rounded overflow-hidden mb-4">
                <div class="p-4">
                    <div class="d-flex justify-content-between mb-3">
                        <h5 class="mb-0">${review.user.name}</h5>
                        <div class="ps-2">
                            ${review.rating}<small class="fa fa-star text-primary"></small>
                        </div>
                    </div>
                    <p class="text-body mb-3">${review.comment}</p>
                    <small class="text-muted">Reservation ID: ${review.reservation.reservation_ID}</small>
                </div>
            </div>
        `;
        reviewsContainer.insertAdjacentHTML('beforeend', reviewHtml);
    });
}