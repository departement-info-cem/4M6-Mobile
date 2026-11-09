---
title: Débogage serveur
description: Débogage serveur seul - Bruno, logs
hide_table_of_contents: true
---

# Débogage serveur

<Row>

<Column>

:::tip Avant la séance (2h)

Déboguer un serveur (ou confirmer qu'il fonctionne bien) vous aidera à préciser davantage d'où provient une erreur. Dans ce cours, nous allons exécuter le [serveur fourni](https://github.com/departement-info-cem/4M6-Mobile/tree/main/code/DebugServer) et nous montrerons les étapes pour déboguer la première erreur.

<Video url="https://www.youtube.com/watch?v=O4KVfoFoxAg" />

:::

</Column>

<Column>

:::info Séance

Nous allons discuter des différents cas où on a utilisé des outils de débogage et pourquoi:
- Layout Inspector
- App Inspection > Network Inspector 
- Point d'arrêt / "evaluate expression" 
- 3 points d'arrêt pour appel asynchrone
- h2-console
- Bruno
- log et traces 

En démo, on va voir Bruno comme notre principal outil pour envoyer des requêtes au serveur que ce soit GET ou POST.

On va voir que les logs (System.out.println, logcat) servent à suivre des sessions de travail et à voir les valeurs quand on ne veut pas arrêter l'exécution avec des points d'arrêt.

Nous allons déboguer le premier problème dans le projet Serveur ensemble. Le reste en exercice

On parlera rapidement des options de débogage d'un serveur déployé en production : on peut lire les logs, mais on ne peut pas mettre de point d'arrêt.

:::

</Column>

</Row>

:::note Exercice

<Etiquette type="main" />

Vous devez compléter le débogage du projet vu en cours afin de le rendre opérationnel. Voici la liste des requêtes à effectuer dans Bruno :

1. Indiquez le problème.
   - localhost:8080/req1 en POST avec, dans le Body, un Raw JSON contenant ["Tomate", "Jambon"]
2. Indiquez les lignes de code causant les problèmes et pourquoi selon vous.
   - localhost:8080/req2 en GET
3. Expliquez pourquoi cette requête semble fonctionner, alors que non. Trouvez ensuite quelle méthode cause problème.
   - localhost:8080/req3 en GET

:::

:::note Exercice déboguer du code généré

<Etiquette type="main" />

Le professeur fournit un petit serveur **généré par Claude** qui passe ses tests mais contient 3 bogues. Sans IA, avec Bruno, les logs et le débogueur :

1. trouve chaque bogue et la requête qui le révèle;
2. explique pourquoi les tests générés ne l'ont pas détecté;
3. ajoute le test qui l'aurait détecté, puis corrige.

:::

## Claude à l'aveugle, puis avec des logs

Le professeur fournit **AppMystère** : une app Android et son serveur, dans un état lamentable. Aucun log, des `catch` vides qui avalent les exceptions, et un bogue qui n'apparaît qu'à l'exécution : « pour un de mes groupes, l'app dit qu'il n'y a aucune note ».

Sans logs, une IA ne voit que le code : elle **devine**. Avec des logs et un moyen de reproduire le bogue elle-même, elle peut **diagnostiquer**. Cet exercice compare les deux.

### Étape 1 : Claude à l'aveugle <Etiquette type="claude" />

Fais un commit de départ, puis donne seulement le symptôme à Claude : « pour un de mes groupes, l'app dit qu'il n'y a aucune note, corrige ».

Dans `appmystere/IA.md`, note :

- ses hypothèses, et combien il en propose;
- les fichiers qu'il modifie (y en a-t-il sans rapport avec le bogue?);
- si le bogue est vraiment corrigé, et s'il sait **expliquer pourquoi**.

Puis annule tout :

```bash
git restore . && git clean -fd
```

### Étape 2 : rendre l'app observable <Etiquette type="main" />

À la main, sans IA :

1. Remplace chaque `catch` vide par un log de l'exception (`Log.e` côté Android, `logger.error` côté serveur).
2. Côté serveur, un `Logger` SLF4J dans chaque contrôleur et service : une ligne par requête reçue (route, utilisateur, durée, code de réponse).
3. Côté Android, ajoute un `HttpLoggingInterceptor` à OkHttp et un `Log.d("AppMystere", ...)` à chaque étape importante (appel lancé, réponse reçue, état mis à jour).
4. Vérifie qu'**aucun** mot de passe, cookie ou jeton n'apparaît dans les logs. (Pourquoi est-ce une faille?)

### Étape 3 : la boucle de rétroaction <Etiquette type="main" />

Écris `repro.sh`, qui reproduit le symptôme et récupère **tous** les logs dans `logs/` :

```bash
#!/bin/bash
# Reproduit le bogue et collecte les logs serveur + Android dans logs/
mkdir -p logs
adb logcat -c
# ... lancer le serveur (sortie vers logs/serveur.log), installer et lancer l'app,
# provoquer le scénario (adb shell input tap / curl), attendre ...
adb logcat -d -s AppMystere:* OkHttp:* AndroidRuntime:* > logs/android.log
```

Puis ajoute à la racine un `CLAUDE.md` qui explique à Claude comment s'en servir : « Pour diagnostiquer, lance `./repro.sh` puis lis `logs/`. Ne corrige rien avant d'avoir trouvé la cause dans les logs. »

### Étape 4 : Claude avec des yeux <Etiquette type="claude" />

Nouvelle session, même symptôme. Observe la boucle : hypothèse → log ciblé → `./repro.sh` → lecture des logs → correctif → `./repro.sh` pour confirmer.

### Étape 5 : comparer <Etiquette type="main" />

Dans `appmystere/IA.md` :

- à l'aveugle vs avec logs : nombre d'essais, temps, justesse du correctif;
- quelle ligne de log a été décisive;
- les logs ajoutés par Claude pendant l'enquête : lesquels garder (et à quel niveau : `debug`, `info`, `error`), lesquels retirer;
- en production, tu n'auras ni débogueur ni `adb` : qu'est-ce que la boucle devient? (indice : [déploiement réel](deploiement-reel), logs de l'hébergeur)
