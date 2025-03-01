document.getElementById('houses-container').addEventListener('click', async function (event) {
    if (event.target.classList.contains('btn-success') || event.target.classList.contains('btn-danger')) {
        const houseColumn = event.target.closest(".col-lg-4"); // Select the whole column
        const houseId = houseColumn.getAttribute('data-house-id'); // Get the house ID (assuming it's stored)

        if (event.target.classList.contains('btn-success')) {
            await acceptHouse(houseId);
        } else {
            await denyHouse(houseId);
        }

        houseColumn.remove(); // Remove the entire column from the grid
    }
});


async function acceptHouse(houseId) {
    await fetch(`/acceptHouse/${houseId}`, { method: 'POST' });
}

async function denyHouse(houseId) {
    await fetch(`/denyHouse/${houseId}`, { method: 'DELETE' });
}