document.addEventListener("DOMContentLoaded", function () {
    const cityInput = document.getElementById('cityInput');
    const searchButton = document.getElementById('searchButton');
    const searchResults = document.getElementById('searchResults');
    let xhr; // Declare the variable to hold the XMLHttpRequest

    searchButton.addEventListener('click', function () {
        const inputText = cityInput.value;
        if (inputText.length >= 1) {
            if (xhr) {
                xhr.abort(); // Abort any ongoing request
            }

            xhr = new XMLHttpRequest();
            xhr.open("GET", `http://localhost:8080/weather/main_page/citysuggestions?query=${inputText}`);
            xhr.responseType = "json";
            xhr.send();

            xhr.onload = function () {
                if (xhr.status === 200) {
                    const cityData = xhr.response;
                    displaySearchResults(cityData); // Display search results
                } else {
                    console.log(`Error: ${xhr.status}`);
                }
            };
        }
    });

    function displaySearchResults(data) {
        searchResults.innerHTML = ''; // Clear existing results

        if (data.length === 0) {
            const noResultsDiv = document.createElement('div');
            noResultsDiv.classList.add('no-results-message'); // Add styling class
            noResultsDiv.textContent = 'No locations found';
            searchResults.appendChild(noResultsDiv);
        }
        else {
            data.forEach(location => {
                const locationDiv = document.createElement('div');
                locationDiv.classList.add('location-row'); // Add styling class
                locationDiv.innerHTML = `
                <div class="location-name">${location.display_name}</div>
                <div class="location-coordinates">Lat: ${location.lat}, Long: ${location.lon}</div>
                <button class="btn btn-primary">Add</button>
            `;
                searchResults.appendChild(locationDiv);
            });
        }
    }

});


