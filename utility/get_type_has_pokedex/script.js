//Mapping DB's id and name
const mappaIdTipi = new Map([
  ["normal", 39],
  ["fire", 40],
  ["water", 41],
  ["electric", 42],
  ["grass", 43],
  ["ice", 44],
  ["fighting", 45],
  ["poison", 46],
  ["ground", 47],
  ["flying", 48],
  ["psychic", 49],
  ["bug", 50],
  ["rock", 51],
  ["ghost", 52],
  ["dragon", 53],
  ["dark", 54],
  ["steel", 55],
  ["fairy", 56],
]);

//This function, cheange the string for the type with a number basing on the previous map
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
