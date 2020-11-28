# HomeCoords

HomeCoords in a Spigot plugin that allows players to save home location(s) without teleporation features.  The plugin is great for servers that want to have the features of the famous `/sethome` and `/home` commands, while still allowing for a vanilla experience.

# Features
* The famous `/sethome` and `/home` commands!
* Multiple home support with its own permission!
* Easy-to-use configuration!
* Simple permissions!
* Lightweight!
* Administrative control!

# Usage
The plugin has 3 commands and permissions.

**Commands**

`/sethome [name] [description]` - Saves a home based on a player's current coordinates.  If the player has the `homecoords.multiple` permission, they can add multiple homes with custom names & descriptions.

`/home [name]` - View a player's home's coordinates.

`/hca` - The base admin command.  Admins must have the `homecoords.admin` permission to access its arguments.
* `info <player> <home>` - Info about a specific player's home.
* `set <player> <home> <x> <z> [description]` - Sets a new or existing player's home's coordinates and information.  Description will be "N/A" if empty.
* `reset <player> <home>` - Resets a player's home's coordinates.
* `list <player>` - List a player's homes.
* `reload` - Reloads the configuration file.

**Permissions**

* `homecoords.basic` - Access to `/sethome` (1 home) and `/home`.
* `homecoords.multiple` - Allows a player/group to have 2+ homes.
* `homecoords.admin` - Access to `/hca` and its arguments.

# Version History
`v2.5`:
* Description support for homes with custom names
* Configuration update
* Reworked interface

`v2.0`:
* Multiple home support
* Configuration update
* Custom name support
* Admin commands extended to include `info` (get) and `list`

`v1.0`:
* Initial release
* Single home support
* X & Z coordinate storage
* Admin commands limited to `get`, `set`, `reset`, and `reload`

# Support
I will only provide support to servers that are actively running the plugin.

# Disclaimer
I am a Java novice.  While other developers have heavily structured plugins with independent class files and advanced options, mine runs in one class only.  I am still learning the language, so there may be parts in here that can be heavily simplified.  Nevertheless, the code works.  If there is an issue, I will attempt to resolve it.  If there is a suggestion, I will attempt to add it.  If you do plan to criticize the code, be positive while still suggesting new ideas.  I stopped for a while because I was always criticized with hatred.
