---
title: Les lambdas
description: Capsule - une fonction qu'on passe comme une valeur, en Kotlin et dans Compose
---

# Capsule : les lambdas

Une **lambda** est une fonction sans nom qu'on range dans une variable ou qu'on passe à une autre fonction. Compose en est rempli : `onClick = { … }`, `items(liste) { … }`, `remember { … }`.

## La forme

```kotlin
val double: (Int) -> Int = { x -> x * 2 }
println(double(4))          // 8

val salut: () -> Unit = { println("Salut") }
salut()                      // Salut
```

- Le **type** se lit : `(paramètres) -> type de retour`. `Unit` = ne retourne rien d'utile.
- Avec un seul paramètre, on peut l'appeler `it` : `{ it * 2 }`.
- La **dernière expression** est la valeur retournée, sans `return`.

## Définir n'est pas exécuter

```kotlin
var compteur = 0
val incremente = { compteur++ }   // rien ne se passe ici
println(compteur)                 // 0
incremente()
incremente()
println(compteur)                 // 2
```

C'est le piège n° 1 en trace : à la ligne où la lambda est **écrite**, son corps ne s'exécute pas. Il s'exécutera à chaque **appel**.

## Dans Compose

```kotlin
@Composable
fun Ecran(onEnvoyer: (String) -> Unit) {
    var texte by remember { mutableStateOf("") }
    TextField(value = texte, onValueChange = { texte = it })
    Button(onClick = { onEnvoyer(texte) }) { Text(stringResource(R.string.envoyer)) }
}
```

- `onValueChange = { texte = it }` : appelée par le `TextField` à chaque frappe, avec le nouveau texte en `it`.
- `onClick = { onEnvoyer(texte) }` : appelée **au clic**, pas à l'affichage. Elle lit `texte` au moment du clic.
- `onEnvoyer` vient de l'appelant : c'est lui qui décide quoi faire (naviguer, appeler le serveur).

## Avec le réseau

```kotlin
Button(onClick = {
    enAttente = true
    service.groupes().enqueue(object : Callback<List<GroupeDTO>> {
        override fun onResponse(call: Call<List<GroupeDTO>>, response: Response<List<GroupeDTO>>) {
            enAttente = false
            groupes = response.body() ?: emptyList()
        }
        override fun onFailure(call: Call<List<GroupeDTO>>, t: Throwable) { enAttente = false }
    })
}) { … }
```

Trois moments différents : le clic (la lambda `onClick` s'exécute et **se termine** tout de suite), l'envoi de la requête, puis plus tard `onResponse` ou `onFailure`. Une trace qui met `groupes = …` juste après `enqueue` est fausse.

## Vérifie-toi

1. Qu'affiche ce code? `val f = { x: Int -> x + 1 }; val g = { x: Int -> f(f(x)) }; println(g(3))`
2. Dans `Button(onClick = { compteur++ })`, combien de fois `compteur++` s'exécute-t-il quand l'écran se recompose 5 fois sans clic?
3. Pourquoi `remember { mutableStateOf(0) }` prend-il une lambda et pas directement `mutableStateOf(0)`?

<details>
<summary>Réponses</summary>

1. `5`.
2. Zéro fois : la lambda est recréée, pas appelée.
3. Pour que la création ne se fasse **qu'une fois** : `remember` n'exécute la lambda qu'au premier affichage, puis rend la valeur gardée.

</details>
