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

console.log(mappaIdTipi);

//Prende in ingresso la mappa
const getCombinationFromType = async function (typesMap) {
  const typeCombination = [];

  const types = Array.from(mappaIdTipi.keys());

  await Promise.all(
    types.map(async (type) => {
      const URL = `https://pokeapi.co/api/v2/type/${type}`;

      const response = await fetch(URL);
      const data = await response.json();

      //Estraggo dalla risposta nome e un oggetto
      //che contiene tre array ognuno per un tipo di debolezza agli attacchi
      const {
        name,
        damage_relations: { double_damage_to, half_damage_to, no_damage_to },
      } = await data;

      //Pusho in un array un oggetto con il nome del pokemon attaccante e un array con le coppie di tipo e tipo di debolezza
      typeCombination.push({
        att: typesMap.get(name),
        def: [
          double_damage_to
            .map((object) => typesMap.get(object.name))
            .map((type) => [3, type]),
          half_damage_to
            .map((object) => typesMap.get(object.name))
            .map((type) => [1, type]),
          no_damage_to
            .map((object) => typesMap.get(object.name))
            .map((type) => [0, type]),
        ].flat(),
      });
    })
  );
  return typeCombination;
};

const typeAndWeakness = getCombinationFromType(mappaIdTipi);
console.log(typeAndWeakness);

const createManyToManyArray = async function (arr) {
  const typeCombination = await arr;
  return typeCombination
    .map((obj) =>
      obj.def.map(([weaknessId, defTypeId]) => [obj.att, defTypeId, weaknessId])
    )
    .flat();
};

const allTypesCombination = createManyToManyArray(typeAndWeakness);
console.log(allTypesCombination);

const createInsertQuery = async function (combinationsArray) {
  const array = await combinationsArray;

  return array.reduce(
    (string, [attTypeId, defTypeId, weaknessId], i) =>
      string +
      `(${attTypeId}, ${defTypeId}, ${weaknessId})${
        i < array.length - 1 ? ",\n" : ";"
      }`,
    "INSERT INTO `type_combination` (`att_type_id`, `def_type_id`, `type_of_weakness_weakness_id`) VALUES\n"
  );
};

console.log(createInsertQuery(allTypesCombination));
