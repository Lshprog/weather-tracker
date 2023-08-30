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

    function addLocation(location) {
        const xhr = new XMLHttpRequest();
        xhr.open("POST", "http://localhost:8080/weather/main_page/addLocation");
        xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        xhr.responseType = "json";

        xhr.onreadystatechange = function () {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    // Add the new location to the DOM
                    const newLocation = xhr.response;
                    displayNewLocation(newLocation);
                } else {
                    console.log(`Error: ${xhr.status}`);
                }
            }
        };

        xhr.send(JSON.stringify(location));
    }

    function displayNewLocation(newLocation) {
        const locationDiv = document.createElement('div');
        locationDiv.classList.add('location-row');
        locationDiv.innerHTML = `
        <div class="location-name">${newLocation.display_name}</div>
        <div class="location-coordinates">Lat: ${newLocation.lat}, Long: ${newLocation.lon}</div>
        <button class="btn btn-primary add-button">Add</button>
    `;

        const addButton = locationDiv.querySelector('.add-button');
        addButton.addEventListener('click', function() {
            addLocation(newLocation);
        });

        searchResults.appendChild(locationDiv);
    }


});


