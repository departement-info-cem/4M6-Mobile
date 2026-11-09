---
title: 🏴‍☠️ CTF
description: 🏴‍☠️ Exercice noté - Capture du drapeau
hide_table_of_contents: true
---

# 🏴‍☠️ Exercice noté - Capture du drapeau

<Row>

<Column>

:::tip Avant la séance (2h)

Assurez-vous de réviser le contenu vu dans les séances de sécurité

- [Injection SQL & Javascript](injection)
- [Contrôle d'accès](controle-acces)

Réfléchissez d'avance à comment vous allez pouvoir exploiter la faille de sécurité concernant **le contrôle d'accès sur la mise à jour du pourcentage d'une tâche**.

:::

</Column>

<Column>

:::info Séance

Le professeur va exécuter le serveur en local sur son poste.

Vous aurez trois heures pour montrer avec succès que vous avez réussi à exploiter la faille. Une fois que vous pensez avoir réussi, levez la main et demandez au professeur de vérifier. Si c'est un succès, rédigez immédiatement les étapes dans le fichier faille.md afin de ne pas les oublier.

:::

</Column>

</Row>

:::note Exercices

### Exercice <Etiquette type="main" />

🐱‍💻 Piratez-moi !👨🏻‍💻🏴‍☠️

### Exercice CTF entre équipes <Etiquette type="main" />

Chaque équipe attaque le serveur de notes **déployé** d'une autre équipe (assignée par le professeur). Comme tout le monde respecte le même `API.md`, tes requêtes Bruno fonctionnent sur tous les serveurs.

Le professeur te donne un compte **étudiant** et un compte **enseignant** sur le serveur ciblé. Objectifs, du plus simple au plus grave :

1. un étudiant lit les notes d'un autre étudiant (accès horizontal);
2. un étudiant crée une évaluation ou saisit une note (élévation de privilège : étudiant → enseignant);
3. un enseignant modifie une note dans un groupe qui n'est pas le sien;
4. un enseignant valide **sa propre** proposition de révision (contournement de la règle du collègue);
5. la recherche d'étudiants par nom est-elle injectable (injection SQL)?
6. un étudiant change **sa propre** note : le drapeau 🏴‍☠️.

Règles d'engagement :

- uniquement le serveur assigné, uniquement pendant la séance;
- interdit : saturer le serveur (pas de déni de service), supprimer des données d'autres utilisateurs que tes comptes de test, attaquer l'hébergeur;
- chaque faille trouvée est rapportée à l'équipe ciblée au format FAILLE / EXPLOIT / CORRECTIF.

Les failles reçues servent à la séance [Durcir sa prod](durcir-prod).

:::
