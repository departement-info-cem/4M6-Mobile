---
title: Multilingue
description: Multilingue I18N - resources dans Android
hide_table_of_contents: true
---

# Images, Multilingue et ressources

<Row>

<Column>

:::tip Avant la séance (2h)

<Video url="https://www.youtube.com/watch?v=kP1o8F9qWfs" />

<Video url="https://www.youtube.com/watch?v=_K-S2nEaoLU" />

:::

</Column>

<Column>

:::info Multilingue, locales

On parlera de stratégie pour déterminer quelle sera la locale par défaut, comment fournir une traduction complète (français) ou une traduction partielle (québécois).

On parlera de la possibilité d'ajouter un logo ou des images pour distinguer son application des autres.

:::

</Column>

</Row>

:::note Exercices de la semaine

## Exercice formatif <Etiquette type="main" />

En préparation de l'examen de la prochaine séance, assurez-vous de bien comprendre comment gérer les ressources multilingues et les images dans Android.

## Exercice Multilingue <Etiquette type="main" />

Vous devez faire une application avec un titre (dans le topBar) et un TextView dans l'écran.

- En anglais, le titre est "SupaDupa app" et le TextView est "expensive"
- En français, le titre est "App en français" et le TextView est "cher"
- En québécois, le titre est "App en français" et le TextView est "dispendieux"

Votre application doit avoir un logo différent de celui par défaut.

## Exercice Logo <Etiquette type="main" />

Vous devez ajouter le logo [suivant](_06-multilingue/logo.jpeg) à votre projet ci-dessus.

## Exercice 6 langues <Etiquette type="claude" />

Dans le projet fil rouge, demande à Claude de traduire **toutes** les chaînes des deux apps en 6 langues : français, anglais, et 4 autres de ton choix (au moins une langue qui s'écrit de droite à gauche, ex. arabe ou hébreu).

## Exercice Validation FR / EN <Etiquette type="main" />

Toi seul peux valider le français et l'anglais :

1. Relis **chaque** chaîne FR et EN dans l'app (pas seulement dans `strings.xml`) et corrige à la main.
2. Vérifie qu'aucune chaîne n'est restée en dur dans le code :

```bash
grep -rn 'Text("' app/src/main/java
```

3. Passe l'émulateur dans chaque langue. En langue de droite à gauche, l'interface s'inverse-t-elle correctement?
4. Note dans `IA.md` les 4 langues que tu **ne peux pas** valider, et comment un vrai projet le ferait.



:::
