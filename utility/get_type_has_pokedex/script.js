//Mapping DB's id and name
const mappaIdTipi = new Map([
  ["normal", 1],
  ["fire", 2],
  ["water", 3],
  ["electric", 4],
  ["grass", 5],
  ["ice", 6],
  ["fighting", 7],
  ["poison", 8],
  ["ground", 9],
  ["flying", 10],
  ["psychic", 11],
  ["bug", 12],
  ["rock", 13],
  ["ghost", 14],
  ["dragon", 15],
  ["dark", 16],
  ["steel", 17],
  ["fairy", 18],
]);

//This function, change the string for the type with a number basing on the previous map
const setTypeId = ([id, types], mappaTipi) => [
  id,
  types.map((nomeTipo) => mappaTipi.get(nomeTipo)),
];

//This function execute the fetch using a pokedex number
const getPokemon = async function (pokedexNumber) {
  const URL = `https://pokeapi.co/api/v2/pokemon/${pokedexNumber}/`;

  const response = await fetch(URL);
  const data = await response.json();
  const pkmn = setTypeId(
    [data.id, [...data.types.map((types) => types.type.name)]],
    mappaIdTipi
  );

  return pkmn;
};

//This function loop the fetch for every pokedex number
const getAllPokemons = async function () {
  const pokemons = [];

  console.log("INIZIO");
  for (let i = 1; i <= 386; i++) {
    pokemons.push(await getPokemon(i));
  }

  return pokemons.flatMap(([id, tipi]) => tipi.map((tipo) => [id, tipo]));
};

console.log(getAllPokemons());

//This function create an insert query basing on eery fetch
const createQuery = async function (pokemons) {
  const pkmns = await pokemons;
  return pkmns.reduce(
    (string, [id, tipo], i) =>
      string + `(${tipo}, ${id})${i < pkmns.length - 1 ? ",\n" : ";"}`,
    "INSERT INTO `type_has_pokedex` (`type_id`, `pokedex_number`) VALUES\n"
  );
};

//Print and then I copy and paste the query on a script
console.log(createQuery(getAllPokemons()));
