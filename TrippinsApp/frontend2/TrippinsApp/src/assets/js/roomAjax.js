
let currentPage = 0; // Track the current page
const pageSize = 6; // Number of houses to load per request

// Add event listener to the "Load More" button
document.getElementById('load-more').addEventListener('click', async function () {
    try {
        currentPage++; // Increment the page number
        const houses = await fetchHouses(currentPage, pageSize); // Fetch houses
        appendHouses(houses); // Append houses to the container

        // Disable the "Load More" button if there are no more houses
        if (houses.length < pageSize) {
            document.getElementById('load-more').disabled = true;
        }
    } catch (error) {
        console.error('Error fetching houses:', error);
    }
});

// Function to fetch houses from the server
async function fetchHouses(page, size) {
    try {
        const url = `v1/api/rooms/extra?page=${page}&size=${size}`;

        const response = await fetch(url, {
            method: 'GET', // Use GET 
            headers: {
                'Accept': 'application/json',
            }
        });


        if (!response.ok) {
            const errorText = await response.text();
            console.error("Server response:", errorText);
            throw new Error(`Failed to fetch houses: ${errorText}`);
        }

        const data = await response.json();
        return data.content; // Return the list of houses
    } catch (error) {
        console.error('Error fetching houses:', error);
        throw error; // Re-throw the error to handle it in the calling function
    }
}

// Function to append houses to the container
function appendHouses(houses) {
    const housesContainer = document.getElementById('houses-container');
    houses.forEach(house => {
        const houseHtml = `
            <div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.1s">
                <a href="/room/${house.code}" class="room-link">
                    <div class="room-item shadow rounded overflow-hidden">
                        <div class="position-relative">
                            <img style="width: 450px; height: 275px" class="img-fluid" src="/v1/api/houses/${house.code}/image" alt="${house.name}">
                            <small class="position-absolute start-0 top-100 translate-middle-y bg-primary text-white rounded py-1 px-3 ms-4">$${house.price}/Night</small>
                        </div>
                        <div class="p-4 mt-2">
                            <div class="d-flex justify-content-between mb-3">
                                <h5 class="mb-0">${house.name}</h5>
                                <div class="ps-2">
                                    ${house.stars}<small class="fa fa-star text-primary"></small>
                                </div>
                            </div>
                            <p class="text-body mb-3">${house.description}</p>
                        </div>
                    </div>
                </a>
            </div>
        `;
        housesContainer.insertAdjacentHTML('beforeend', houseHtml);
    });
}