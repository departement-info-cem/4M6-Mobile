// @ts-check

// Dates H27 PROVISOIRES (relâche la semaine du 1er mars) : à remplacer par le vrai
// calendrier des groupes dès qu'il est connu. Joris : lundi et mercredi; Benoît : mardi et jeudi.
let joris = {
  1: "2027-01-25",
  2: "2027-01-27",
  3: "2027-02-01",
  4: "2027-02-03",
  5: "2027-02-08",
  6: "2027-02-10",
  7: "2027-02-15",
  8: "2027-02-17",
  9: "2027-02-22",
  10: "2027-02-24",
  11: "2027-03-08",
  12: "2027-03-10",
  13: "2027-03-15",
  14: "2027-03-17",
  15: "2027-03-22",
  16: "2027-03-24",
  17: "2027-03-29",
  18: "2027-03-31",
  19: "2027-04-05",
  20: "2027-04-07",
  21: "2027-04-12",
  22: "2027-04-14",
  23: "2027-04-19",
  24: "2027-04-21",
  25: "2027-04-26",
  26: "2027-04-28",
  27: "2027-05-03",
  28: "2027-05-05",
  29: "2027-05-10",
  30: "2027-05-12",
};

let benoit = {
  1: "2027-01-26",
  2: "2027-01-28",
  3: "2027-02-02",
  4: "2027-02-04",
  5: "2027-02-09",
  6: "2027-02-11",
  7: "2027-02-16",
  8: "2027-02-18",
  9: "2027-02-23",
  10: "2027-02-25",
  11: "2027-03-09",
  12: "2027-03-11",
  13: "2027-03-16",
  14: "2027-03-18",
  15: "2027-03-23",
  16: "2027-03-25",
  17: "2027-03-30",
  18: "2027-04-01",
  19: "2027-04-06",
  20: "2027-04-08",
  21: "2027-04-13",
  22: "2027-04-15",
  23: "2027-04-20",
  24: "2027-04-22",
  25: "2027-04-27",
  26: "2027-04-29",
  27: "2027-05-04",
  28: "2027-05-06",
  29: "2027-05-11",
  30: "2027-05-13",
};

/** @type {import('@docusaurus/plugin-content-docs').SidebarsConfig} */
const sidebars = {
  // H27 : 30 séances. 🤖 = Claude Code, ✍️ = à la main, 📝 = test papier 10 min, 💻 = examen sans IA
  docs: [
    {
      type: "doc",
      label: "1 - Plan de cours + le choc 🤖",
      id: "cours/choc-claude",
      customProps: {
        calendrier: {
          Joris: [{ 1010: joris[1] }],
          Benoît: [{ 1020: benoit[1] }],
        },
      },
    },
    {
      type: "doc",
      label: "2 - Bilan du choc, IA web ou IA avec le code",
      id: "cours/revision",
      customProps: {
        calendrier: {
          Joris: [{ 1010: joris[2] }],
          Benoît: [{ 1020: benoit[2] }],
        },
      },
    },
    {
      type: "doc",
      label: "3 - Mise en page 📝",
      id: "cours/mise-en-page",
      customProps: {
        calendrier: {
          Joris: [{ 1010: joris[3] }],
          Benoît: [{ 1020: benoit[3] }],
        },
      },
    },
    {
      type: "doc",
      label: "4 - Listes, navigation, Scaffold",
      id: "cours/listes",
      customProps: {
        avancementLabel: "TP1",
        avancement: 0.2,
        calendrier: {
          Joris: [{ 1010: joris[4] }],
          Benoît: [{ 1020: benoit[4] }],
        },
      },
    },
    {
      type: "doc",
      label: "5 - Identité visuelle",
      id: "cours/identite-visuelle",
      customProps: {
        avancementLabel: "TP1",
        avancement: 0.5,
        calendrier: {
          Joris: [{ 1010: joris[5] }],
          Benoît: [{ 1020: benoit[5] }],
        },
      },
    },
    {
      type: "doc",
      label: "6 - 6 langues 📝",
      id: "cours/multilingue",
      customProps: {
        calendrier: {
          Joris: [{ 1010: joris[6] }],
          Benoît: [{ 1020: benoit[6] }],
        },
      },
    },
    {
      type: "doc",
      label: "7 - HTTP / Retrofit — remise TP1",
      id: "cours/http",
      className: "remise-tp1",
      customProps: {
        avancementLabel: "TP1 - Remis",
        avancement: 1,
        calendrier: {
          Joris: [{ 1010: joris[7] }],
          Benoît: [{ 1020: benoit[7] }],
        },
      },
    },
    {
      type: "doc",
      label: "8 - Réseau : POST, DTO",
      id: "cours/http",
      customProps: {
        calendrier: {
          Joris: [{ 1010: joris[8] }],
          Benoît: [{ 1020: benoit[8] }],
        },
      },
    },
    {
      type: "doc",
      label: "9 - Réseau réel 📝",
      id: "cours/http",
      customProps: {
        calendrier: {
          Joris: [{ 1010: joris[9] }],
          Benoît: [{ 1020: benoit[9] }],
        },
      },
    },
    {
      type: "doc",
      label: "10 - Spring Boot",
      id: "cours/spring-java",
      customProps: {
        avancementLabel: "TP2",
        avancement: 0.1,
        calendrier: {
          Joris: [{ 1010: joris[10] }],
          Benoît: [{ 1020: benoit[10] }],
        },
      },
    },
    {
      type: "doc",
      label: "11 - Cycle de vie Spring",
      id: "cours/cycle-vie-spring",
      customProps: {
        calendrier: {
          Joris: [{ 1010: joris[11] }],
          Benoît: [{ 1020: benoit[11] }],
        },
      },
    },
    {
      type: "doc",
      label: "12 - Spring par Claude 📝",
      id: "cours/spring-par-claude",
      customProps: {
        calendrier: {
          Joris: [{ 1010: joris[12] }],
          Benoît: [{ 1020: benoit[12] }],
        },
      },
    },
    {
      type: "doc",
      label: "13 - Formatif intra 💻",
      id: "cours/formatif-intra",
      customProps: {
        calendrier: {
          Joris: [{ 1010: joris[13] }],
          Benoît: [{ 1020: benoit[13] }],
        },
      },
    },
    {
      type: "doc",
      label: "14 - Examen intra 💻",
      id: "cours/examen",
      className: "examen",
      customProps: {
        calendrier: {
          Joris: [{ 1010: joris[14] }],
          Benoît: [{ 1020: benoit[14] }],
        },
      },
    },
    {
      type: "doc",
      label: "15 - Débogage, Claude et les logs",
      id: "cours/debogage",
      customProps: {
        avancementLabel: "TP2",
        avancement: 0.4,
        calendrier: {
          Joris: [{ 1010: joris[15] }],
          Benoît: [{ 1020: benoit[15] }],
        },
      },
    },
    {
      type: "doc",
      label: "16 - Worktrees, Claude en parallèle",
      id: "cours/worktrees",
      customProps: {
        avancementLabel: "TP2",
        avancement: 0.7,
        calendrier: {
          Joris: [{ 1010: joris[16] }],
          Benoît: [{ 1020: benoit[16] }],
        },
      },
    },
    {
      type: "doc",
      label: "17 - Claude hors du cours : le cloud",
      id: "cours/claude-cloud",
      customProps: {
        avancementLabel: "TP2",
        avancement: 0.85,
        calendrier: {
          Joris: [{ 1010: joris[17] }],
          Benoît: [{ 1020: benoit[17] }],
        },
      },
    },
    {
      type: "doc",
      label: "18 - Erreurs HTTP, GUI, attente — remise TP2 📝",
      id: "cours/erreur-http",
      className: "remise-tp2",
      customProps: {
        avancementLabel: "TP2 - Remis",
        avancement: 1,
        calendrier: {
          Joris: [{ 1010: joris[18] }],
          Benoît: [{ 1020: benoit[18] }],
        },
      },
    },
    {
      type: "doc",
      label: "19 - Déploiement réel I",
      id: "cours/deploiement-reel",
      customProps: {
        avancementLabel: "TP3",
        avancement: 0.1,
        calendrier: {
          Joris: [{ 1010: joris[19] }],
          Benoît: [{ 1020: benoit[19] }],
        },
      },
    },
    {
      type: "doc",
      label: "20 - Déploiement réel II 📝",
      id: "cours/deploiement-reel",
      customProps: {
        calendrier: {
          Joris: [{ 1010: joris[20] }],
          Benoît: [{ 1020: benoit[20] }],
        },
      },
    },
    {
      type: "doc",
      label: "21 - Cybersec : injections",
      id: "cours/injection",
      customProps: {
        avancementLabel: "TP3",
        avancement: 0.3,
        calendrier: {
          Joris: [{ 1010: joris[21] }],
          Benoît: [{ 1020: benoit[21] }],
        },
      },
    },
    {
      type: "doc",
      label: "22 - Cybersec : crypto",
      id: "cours/injection",
      customProps: {
        calendrier: {
          Joris: [{ 1010: joris[22] }],
          Benoît: [{ 1020: benoit[22] }],
        },
      },
    },
    {
      type: "doc",
      label: "23 - Cybersec : contrôle d'accès 📝",
      id: "cours/controle-acces",
      customProps: {
        calendrier: {
          Joris: [{ 1010: joris[23] }],
          Benoît: [{ 1020: benoit[23] }],
        },
      },
    },
    {
      type: "doc",
      label: "24 - Cybersec élargie",
      id: "cours/cybersec-elargie",
      customProps: {
        avancementLabel: "TP3",
        avancement: 0.6,
        calendrier: {
          Joris: [{ 1010: joris[24] }],
          Benoît: [{ 1020: benoit[24] }],
        },
      },
    },
    {
      type: "doc",
      label: "25 - CTF 🏴‍☠️",
      id: "cours/ctf",
      customProps: {
        calendrier: {
          Joris: [{ 1010: joris[25] }],
          Benoît: [{ 1020: benoit[25] }],
        },
      },
    },
    {
      type: "doc",
      label: "26 - Durcir sa prod 📝",
      id: "cours/durcir-prod",
      customProps: {
        calendrier: {
          Joris: [{ 1010: joris[26] }],
          Benoît: [{ 1020: benoit[26] }],
        },
      },
    },
    {
      type: "doc",
      label: "27 - Compatibilité API — remise TP3",
      id: "cours/compatibilite-api",
      className: "remise-tp3",
      customProps: {
        avancementLabel: "TP3 - Remis",
        avancement: 1,
        calendrier: {
          Joris: [{ 1010: joris[27] }],
          Benoît: [{ 1020: benoit[27] }],
        },
      },
    },
    {
      type: "doc",
      label: "28 - Correction TP3, stratégie",
      id: "cours/correction-tp3",
      customProps: {
        calendrier: {
          Joris: [{ 1010: joris[28] }],
          Benoît: [{ 1020: benoit[28] }],
        },
      },
    },
    {
      type: "doc",
      label: "29 - Formatif final 💻",
      id: "cours/formatif-final",
      customProps: {
        calendrier: {
          Joris: [{ 1010: joris[29] }],
          Benoît: [{ 1020: benoit[29] }],
        },
      },
    },
    {
      type: "doc",
      label: "30 - Examen final 💻",
      id: "cours/examen",
      className: "examen",
      customProps: {
        calendrier: {
          Joris: [{ 1010: joris[30] }],
          Benoît: [{ 1020: benoit[30] }],
        },
      },
    },
    {
      type: "doc",
      label: "📝 Tests papier",
      id: "cours/tests-papier",
    },
  ],
  tp: [{ type: "autogenerated", dirName: "02-tp" }],
  recettes: [{ type: "autogenerated", dirName: "03-recettes" }],
};

module.exports = sidebars;
