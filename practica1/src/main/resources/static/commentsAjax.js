let currentPage = 0; // Track the current page
const pageSize = 3; // Number of comments to load per request

// Add event listener to the "Load More" button
document.getElementById('load-more').addEventListener('click', async function () {
    try {
        currentPage++; // Increment the page number
        const hotelId = document.getElementById("houseIdHidden").value;
        const comments = await fetchComments(hotelId, currentPage, pageSize); // Fetch comments
        appendComments(comments); // Append comments to the container

        colorRatingBars();
        // Disable the "Load More" button if there are no more comments
        if (comments.length < pageSize) {
            document.getElementById('load-more').disabled = true;
        }
    } catch (error) {
        console.error('Error fetching comments:', error);
    }
});


// Function to fetch comments from the server
async function fetchComments(hotelId,page, size) {
    try {
        const response = await fetch('/loadComments', {
            method: 'POST', // Use POST 
            headers: {
                'Content-Type': 'application/json', // Specify the content type as JSON
            },
            body: JSON.stringify({hotelId, page, size }), // Send page and size in the request body
        });

        if (!response.ok) {
            const errorText = await response.text();
            console.error("Server response:", errorText);
            throw new Error(`Failed to fetch comments: ${errorText}`);
        }

        const data = await response.json();
        return data.content; // Return the list of comments
    } catch (error) {
        console.error('Error fetching comments:', error);
        throw error; // Re-throw the error to handle it in the calling function
    }
}

// Function to append comments to the container
function appendComments(comments) {
    const commentsContainer = document.getElementById('comment-container'); 

    comments.forEach(comment => {
        const commentHtml = `
            <div class="comment-box border rounded p-4 mb-3 shadow-sm position-relative" style="background-color: rgba(39, 58, 77, 0.1);">
                <!-- Quotation Icon -->
                <i class="fa fa-quote-right fa-2x text-primary position-absolute top-0 end-0 me-3 mt-3"></i>

                <div class="d-flex align-items-center mb-2">
                    <i class="bi bi-person-circle me-2" style="font-size: 2rem;"></i>
                    <h5 class="m-0">${comment.user.name}</h5>
                </div>
                <p class="mb-2 text-break">${comment.comment}</p>
                <strong class="text-dark">Rating: ${comment.rating}/100</strong>
                <!-- Barra de rating -->
                        <div class="rating-bar">
                            <div class="rating-fill" style="width: ${comment.rating}%;"></div>
                        </div>
            </div>
        `;
        commentsContainer.insertAdjacentHTML('beforeend', commentHtml);
    });
}

function colorRatingBars() {
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
    
}