---
title: Causerie DTO et Cookie
description: Causerie - Retour sur DTO et cookie
hide_table_of_contents: true
---

# Causerie : Retour sur DTO et cookie

<Row>

<Column>

:::tip Avant la séance (2h)

Essayez de répondre aux questions suivantes en utilisant ce que vous avez compris de ces concepts :

- Qu'est-ce qu'un DTO?
- Pourquoi on utilise les DTO?
- Qui utilise les DTO?

- Qu'est-ce qu'un cookie?
- Qui crée et qui utilise le cookie?
- Est-ce que la gestion des cookies est pareille partout?

:::

</Column>

<Column>

:::info Séance

Nous aurons une discussion pour répondre aux questions ci-contre. Le but est de consolider votre compréhension de ces deux concepts clés de programmation :

- Comment sont gérés les cookies avec Spring Boot Security?
- Qu'est-ce que cela a comme conséquence avec les sessions d'un usager?
- En quoi est-ce différent ou pareil à la procédure utilisée en .net?

:::

</Column>

</Row>

:::note Exercice interactif

##  Exercice formatif <Etiquette type="main" />

En préparation de l'examen de la prochaine séance, assurez-vous de bien comprendre l'utilité des DTO et le fonctionnement des cookies pour la gestion de session.

En équipe de 3 ou 4, étudiez les mises en situation et formulez une solution potentielle au problème.

## Krypt'Eau Raideur

Vous décidez de créer un jeu. Vous souhaitez **réduire le nombre de requêtes/réponses** et **éviter l'envoi inutile de données** dans chaque requête ou réponse, entre autres pour optimiser la fluidité du jeu.

Pour jouer, les participants devront se créer un héros. Voici la classe modèle :

```java showLineNumbers
public class hero {
	Long id;
	String nom;
	int generation;
	int niveau;
	Date dateNaissance;
	String race; // orc, elfe, humain
	String job; // magicien, archer, guerrier
	int strength;
	int agility;
	int magic;
	Date dateEmbauche;
	String nomPoste; // explorateur, collecteur, mineur
	Float salaire;
	Arme monArme;
	boolean enQuete;
}
```

### Exercice 1 : Voir le héros <Etiquette type="main" />

Votre première tâche est de créer le DTO qui permettra d'afficher les informations suivantes. Notez que l'image affichée est aléatoire.

![miami](_24-causerie-dto-cookie/Miami1.png)

### Exercice 2 : Transfer vs View Model <Etiquette type="main" />

On veut maintenant créer une interface afin d'afficher quelques informations intéressantes. Le designer a fait un brouillon :

![miami 2](_24-causerie-dto-cookie/Miami2.png)

Votre deuxième tâche est de créer le ou les DTO pour permettre d'afficher les informations ci-dessus. Réfléchissez à où devraient s'effectuer les calculs, de quelles informations le client a besoin, etc.

### Exercice 3 : Création d'un héros <Etiquette type="main" />

Lorsqu'un joueur se crée un nouveau héros, les actions suivantes doivent être exécutées :

1. On crée le héros avec ses informations de base.
2. On donne une arme au héros (toujours le Wooden Woopa Stick).
3. On envoie le héros en quête.

Votre troisième tâche est de créer un ou des DTO de façon à réduire au maximum le nombre de requêtes/réponses.

## Insta Groom

<Row>

<Column>

![Instagroom](_24-causerie-dto-cookie/logo.jpg)

</Column>
<Column/>
<Column/>
<Column/>
<Column/>
<Column/>
<Column/>

</Row>

Vous avez eu une idée de génie : coder un concurrent d'Instagram, mais pour les gens *bien groomés* uniquement.

Voici votre classe de modèle :

```java
public class profilInstaGroom {
	Long id;
	String nomUtilisateur;
	String alias; // @blabla
	String bio;
	boolean estVerifie;
	TypeCompte monCompte; //business, VIP, normal
	Long nombreMedia;
	String photoPrincipaleURL;
	List<profilInstaGroom> jeFollow;
	Feed feedActuel; 
	int scoreGrooming; //ne peut dépasser 9000
}
```

Psst ! Je me suis inspirée du vrai [Instagram](https://developers.facebook.com/docs/instagram-basic-display-api/reference/user) pour le modèle 😉

### Exercice 4 : Boss final <Etiquette type="main" />

On veut afficher la liste de nos comptes favoris, c'est-à-dire tous ceux que l'on suit (*follow*). Pour chaque compte, on a son nom d'utilisateur, son alias, sa photo, s'il est vérifié et s'il a de nouvelles publications (*nombreMedia*).

Écrivez le ou les DTO pour permettre cet affichage.

![boss](_24-causerie-dto-cookie/Instagroom1.png)



:::
