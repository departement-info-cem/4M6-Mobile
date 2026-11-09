import React, { JSX } from "react";
import "./styles.css";

// Étiquette d'exercice H27 : 🤖 Claude (Claude Code obligatoire) ou ✍️ À la main (aucune IA).
export default function Etiquette({ type }: { type: "claude" | "main" }): JSX.Element {
  return type === "claude" ? (
    <span className="etiquette etiquette-claude" title="Exercice à faire avec Claude Code">🤖 Claude</span>
  ) : (
    <span className="etiquette etiquette-main" title="Exercice à faire à la main, sans IA">✍️ À la main</span>
  );
}
