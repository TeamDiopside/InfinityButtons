file_names = [["none", "a", "b", "c", "d", "e", "f"],
              ["g", "h", "i", "j", "k", "l", "m"],
              ["n", "o", "p", "q", "r", "s", "t"],
              ["u", "v", "w", "x", "y", "z", "0"],
              ["1", "2", "3", "4", "5", "6", "7"],
              ["8", "9", "dot", "comma", "q_mark", "ex_mark", "minus"],
              ["plus", "colon", "equals", "hashtag", "br_open", "br_close", "slash"]]

pressed_bools = [True, False]

faces = ["floor", "wall", "ceiling"]
directions = ["north", "east", "south", "west"]
pressed_strings = ["false", "true"]


def models():
    print("\nStarting models...\n")
    for row in range(7):
        for column in range(7):
            for pressed in pressed_bools:
                row_coord = row * 1.25
                col_coord = column * 1.25
                text = model_text(col_coord + 0.25, row_coord + 0.25, col_coord + 1.25, row_coord + 1.25, pressed)
                name = file_names[row][column] + f"{'_pressed' if pressed else ''}"
                file = open(f"src/main/resources/assets/infinitybuttons/models/block/letter_buttons/{name}.json", "w")
                file.write(text)
                print(f"Generated {name}.json")
    print("\nDone with models!\n")


def model_text(x1, y1, x2, y2, pressed):
    return f"""{{
  "parent": "block/block",
  "textures": {{
    "0": "infinitybuttons:block/letter_button/characters_color"
  }},
  "elements": [
    {{
      "from": [6, {"1" if pressed else "2"}.01, 6],
      "to": [10, {"1" if pressed else "2"}.01, 10],
      "faces": {{
        "up": {{"uv": [{x1}, {y1}, {x2}, {y2}], "texture": "#0"}}
      }}
    }}
  ]
}}
"""


blockstate_start = """{
  "multipart": [
    {
      "when": {"AND": [{"pressed": false}, {"face": "floor"}, {"facing": "north"}]},
      "apply": {
        "model": "infinitybuttons:block/letter_buttons/letter_button"
      }
    }"""

blockstate_end = """
  ]
}"""


def blockstate(file_name):
    print("\nStarting blockstate...\n")
    file = open(file_name, "w")
    file.write(blockstate_start)
    for character in range(49):
        character_name = file_names[character // 7][character % 7]
        for face in range(3):
            for facing in range(4):
                for pressed in pressed_strings:
                    if character == 0 and not (face == 0 and facing == 0 and pressed == "false"):
                        file.write(blockstate_button_text(pressed, face, facing))
                    file.write(blockstate_character_text(character_name, pressed, face, facing))
        print(f"Generated {character_name} in blockstate")
    file.write(blockstate_end)
    print("\nDone with blockstate!\n")


def blockstate_character_text(character, pressed, face, facing):
    return f""",
    {{
      "when": {{"AND": [{{"character": "{character}"}}, {{"pressed": {pressed}}}, {{"face": "{faces[face]}"}}, {{"facing": "{directions[facing]}"}}]}},
      "apply": {{
        "model": "infinitybuttons:block/letter_buttons/{character}{"_pressed" if pressed == "true" else ""}",
        "x": {face * 90 + 180 if face == 1 else face * 90},
        "y": {facing * 90 + 180 if face == 1 else facing * 90}
      }}
    }}"""


def blockstate_button_text(pressed, face, facing):
    return f""",
    {{
      "when": {{"AND": [{{"pressed": {pressed}}}, {{"face": "{faces[face]}"}}, {{"facing": "{directions[facing]}"}}]}},
      "apply": {{
        "model": "infinitybuttons:block/letter_buttons/letter_button{"_pressed" if pressed == "true" else ""}",
        "x": {face * 90 + 180 if face == 1 else face * 90},
        "y": {facing * 90 + 180 if face == 1 else facing * 90}
      }}
    }}"""


models()
blockstate("src/main/resources/assets/infinitybuttons/blockstates/letter_button.json")
blockstate("src/main/resources/assets/infinitybuttons/blockstates/letter_lever.json")
