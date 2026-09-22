# PlayerControl 26.2 - Unofficial Port

> [!WARNING]
> **This is an unofficial community port of PlayerControl for Minecraft 26.2.**
>
> This project is **not affiliated with, endorsed by, or an official release from the original PlayerControl author.**

An unofficial port of [PlayerControl](https://modrinth.com/mod/playercontrol) for **Minecraft 26.2** using **Fabric**.

PlayerControl allows players to swap control with other players and interact with Carpet fake players.

## Features

- Control another player
- Swap control between players
- Release player control
- Control Carpet fake players
- Player control status
- Active control listing
- Optional controller prefix
- Optional permission swapping
- Server-side functionality

## Requirements

- Minecraft **26.2**
- Fabric Loader **0.19.5**
- Fabric API **0.160.0+26.2**
- Java **25**
- Carpet **26.2+v260616** for Carpet fake-player functionality

## Installation

1. Install [Fabric Loader](https://fabricmc.net/use/).
2. Install [Fabric API](https://modrinth.com/mod/fabric-api).
3. Download the PlayerControl 26.2 port from the [Releases](../../releases) page or [Modrinth](https://modrinth.com/).
4. Place the `.jar` file into your server's `mods` folder.
5. Start the server.

### Carpet

Carpet is optional for normal player control.

Carpet is required when using PlayerControl with Carpet fake players.

## Commands

### Swap Control

```text
/controlPlayer swap <player>
