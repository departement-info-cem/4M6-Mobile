---
title: Cycle de vie Spring
description: Quand un contrôleur est-il créé, combien d'instances, et pourquoi l'état dans un champ est dangereux
hide_table_of_contents: true
---

# Cycle de vie Spring

<Row>

<Column>

:::tip Avant la séance

Relis ton serveur `CalculServeur` de la séance précédente. Pour chaque classe, devine :

- **quand** elle est instanciée (au démarrage? à chaque requête?);
- **combien** d'objets existent après 30 requêtes.

Note tes réponses : on les vérifie en classe.

:::

</Column>

<Column>

:::info Séance

- Le conteneur Spring crée les `@Controller`, `@RestController`, `@Service`, `@Repository` **une seule fois, au démarrage** (portée *singleton* par défaut).
- 30 requêtes = **1** contrôleur, **1** service, mais **30 appels** de méthode, souvent en même temps sur plusieurs fils d'exécution (threads).
- Conséquence : un champ modifié dans un contrôleur est **partagé par tous les utilisateurs**.
- Les objets qui changent à chaque requête : paramètres de la méthode, `@RequestBody`, variables locales, DTO retournés.

:::

</Column>

</Row>

## Exercice 1 : compter les instances <Etiquette type="main" />

Dans `CalculServeur`, ajoute **à la main** :

```java
@RestController
public class ControleurCalcul {
    private static int nbInstances = 0;
    private int nbAppels = 0;

    public ControleurCalcul() {
        nbInstances++;
        System.out.println("Constructeur ControleurCalcul, instances = " + nbInstances);
    }

    @GetMapping("/compte")
    public String compte() {
        nbAppels++;
        return "instances=" + nbInstances + " appels=" + nbAppels
                + " fil=" + Thread.currentThread().getName();
    }
}
```

Avant d'exécuter, écris ta prédiction, puis envoie 30 requêtes :

```bash
for i in $(seq 1 30); do curl -s localhost:8080/compte; echo; done
```

1. Combien de fois le constructeur s'affiche-t-il? À quel moment?
2. Que vaut `appels` à la 30e requête? Pourquoi n'est-il pas remis à 0?
3. Le nom du fil change-t-il? Qu'est-ce que ça dit sur le serveur?

## Exercice 2 : le bogue de concurrence <Etiquette type="main" />

Envoie maintenant 1000 requêtes **en parallèle** (10 à la fois), puis une dernière :

```bash
seq 1 1000 | xargs -P 10 -I{} curl -s -o /dev/null localhost:8080/compte
curl -s localhost:8080/compte
```

1. Est-ce que `appels` vaut 1001? Recommence 3 fois.
2. Explique pourquoi `nbAppels++` n'est pas une opération atomique.
3. Corrige avec `AtomicInteger` et vérifie.
4. Imagine maintenant un champ `Utilisateur utilisateurCourant` dans un contrôleur. Que se passe-t-il quand deux utilisateurs font une requête en même temps?

## Exercice 3 : et le service? <Etiquette type="main" />

Ajoute un constructeur avec `println` dans `ServiceCalcul` (injecté avec `@Autowired`).

1. Dans quel ordre s'affichent les constructeurs du service et du contrôleur au démarrage? Pourquoi?
2. Si deux contrôleurs injectent le même service, combien de services existe-t-il?
3. Bonus : ajoute `@RequestScope` au contrôleur. Combien d'instances après 30 requêtes maintenant?

:::note À retenir pour les tests papier
On te donnera un serveur et une suite de requêtes, et tu devras dire **combien d'objets** sont créés, **ce qui est affiché** et **ce qui est retourné**.
:::
