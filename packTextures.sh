export CLASSPATH=~/dev/gdx/libgdx-nightly-20140410/extensions/gdx-tools/gdx-tools.jar:~/dev/gdx/libgdx-nightly-20140410/gdx.jar
#java com.badlogic.gdx.tools.texturepacker.TexturePacker assets/game drack-android/assets/data/ game.png

rm drack-android/assets/data/game_skin/ui.png
rm drack-android/assets/data/game_skin/ui.atlas
java com.badlogic.gdx.tools.texturepacker.TexturePacker assets/ui drack-android/assets/data/game_skin ui.png
mv drack-android/assets/data/game_skin/ui.png.atlas drack-android/assets/data/game_skin/ui.atlas

