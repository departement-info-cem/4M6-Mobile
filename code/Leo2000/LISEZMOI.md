# Léo2000 — serveur du TP1

Léo2000 est le serveur de gestion des notes **fourni par les profs** pour le TP1. Tes deux apps Android (enseignant et étudiant) lui parlent. Au TP2, tu ajouteras toi-même de nouvelles routes dans ce code.

Une copie tourne en ligne (adresse donnée en classe). Pour travailler sans réseau ou pour lire ce qui se passe côté serveur, roule-le sur ton poste.

## Démarrer en local

Il faut un JDK 21.

```bash
./gradlew bootRun
```

Le serveur écoute sur `http://localhost:8080`. Depuis l'émulateur Android, cette adresse devient `http://10.0.2.2:8080`.

La BD H2 est dans `db/` : supprime ce dossier pour repartir des données initiales.

Avec Docker, à la place :

```bash
docker build -t leo2000 .
docker run -p 8080:8080 leo2000
```

## Comptes de test

Mot de passe pour tous : `Passw0rd!`

| Courriel | Rôle | Matricule |
|---|---|---|
| `ada@cem.ca` | ENSEIGNANT | — |
| `blaise@cem.ca` | ENSEIGNANT | — |
| `chloe@cem.ca` | ETUDIANT | `2027001` |
| `dany@cem.ca` | ETUDIANT | `2027002` |

Groupes au départ : `420-4M6-EM` (Ada; Chloé et Dany inscrits; évaluations TP1 et Intra) et `420-4N6-EM` (Blaise; Chloé inscrite; évaluation Examen).

## Les routes

L'API complète du TP1 est dans l'énoncé du TP1. Pour la retrouver dans le code : un contrôleur par famille de routes dans `controleurs/`, la logique et les règles d'accès dans `services/`, l'accès à la BD dans `depots/`.

## Où regarder

| Je veux comprendre… | Fichier |
|---|---|
| d'où vient l'utilisateur connecté | `services/Session.java` |
| le JSON reçu et renvoyé | `dto/Dto.java` |
| quel code HTTP est renvoyé en cas d'erreur | `services/ApiException.java`, `controleurs/GestionErreurs.java` |
| les données de départ | `DonneesInitiales.java` |
