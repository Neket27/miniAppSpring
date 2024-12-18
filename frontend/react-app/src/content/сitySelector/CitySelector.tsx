import  { useState } from "react";

interface CitySelectorProps {
    availableCities: string[]; // Список возможных городов
    onSelectCity: (city: string) => void; // Callback при выборе города
}

const CitySelector: React.FC<CitySelectorProps> = ({ availableCities, onSelectCity }) => {
    const [query, setQuery] = useState("");
    const [filteredCities, setFilteredCities] = useState<string[]>([]);

    const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const value = event.target.value;
        setQuery(value);

        if (value.trim() === "") {
            setFilteredCities([]);
            return;
        }

        const filtered = availableCities.filter((city) =>
            city.toLowerCase().startsWith(value.toLowerCase())
        );
        setFilteredCities(filtered);
    };

    const handleCitySelect = (city: string) => {
        setQuery(city);
        setFilteredCities([]);
        onSelectCity(city);
    };

    return (
        <div className="city-selector">
            <input
                type="text"
                value={query}
                onChange={handleInputChange}
                placeholder="Введите название города"
                className="city-input"
            />
            {filteredCities.length > 0 && (
                <ul className="city-list">
                    {filteredCities.map((city) => (
                        <li
                            key={city}
                            onClick={() => handleCitySelect(city)}
                            className="city-item"
                        >
                            {city}
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
};

export default CitySelector;
