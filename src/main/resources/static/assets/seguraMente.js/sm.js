
const API_KEY= '82691d26404f1d44326b661a3923fd3f';

const fetchData = position => {
	const {latitude,longitude} = position.coords;
	fetch (`https://api.openweathermap.org/data/2.5/weather?lang=sp&units=metric&lat=${latitude}&lon=${longitude}&appid=${API_KEY}`)
	.then(response => response.json())
	.then(data=> setWeatherData(data));
	
	console.log(position);
	
}

const setWeatherData = data =>{
	console.log(data);
	const weatherData = {
		lugar: data.name,
		temperatura: Math.floor(data.main.temp)+'˚',
		estado: data.weather[0].description,
		fecha: getDate(),
	}
	
	Object.keys(weatherData).forEach(key =>{
		document.getElementById(key).textContent = weatherData[key];
	}); 
	
	cleanUp();
}

const cleanUp= () => {
	let container = document.getElementById('climaConteiner');
	let cargando = document.getElementById('cargando');
	
	cargando.style.display='none';
	container.style.display='flex';
	
}


const getDate = () =>{
	let date = new Date();
	return `${date.getDate()}-${('0'+ (date.getMonth()+1)).slice(-2)}-${date.getFullYear()}`;
}

const onLoad = () => {
	navigator.geolocation.getCurrentPosition(fetchData);
}