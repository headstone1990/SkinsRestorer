/**
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 *
 */

package skinsrestorer.utils;

import org.json.simple.parser.ParseException;

import net.minecraft.util.com.mojang.authlib.properties.Property;
import net.minecraft.util.com.mojang.util.UUIDTypeAdapter;
import skinsrestorer.com.mojang.api.profiles.Profile;
import skinsrestorer.storage.SkinProfile;

public class SkinGetUtils {

	public static SkinProfile getSkinProfile(String name) throws SkinFetchFailedException {
		try {
			Profile prof = DataUtils.getProfile(name);
			if (prof == null) {
				throw new SkinFetchFailedException(SkinFetchFailedException.Reason.NO_PREMIUM_PLAYER);
			}
			Property prop = DataUtils.getProp(prof.getId());
			if (prop == null) {
				throw new SkinFetchFailedException(SkinFetchFailedException.Reason.NO_SKIN_DATA);
			}
			try {
				return new SkinProfile(UUIDTypeAdapter.fromString(prof.getId()), prop);
			} catch (ParseException e) {
				throw new SkinFetchFailedException(SkinFetchFailedException.Reason.SKIN_RECODE_FAILED, e);
			}
		} catch (Exception e) {
			throw new SkinFetchFailedException(SkinFetchFailedException.Reason.GENERIC_ERROR, e);
		}
	}

	public static class SkinFetchFailedException extends Exception {

		private static final long serialVersionUID = -7597517818949217019L;

		public SkinFetchFailedException(Reason reason) {
			super(reason.getExceptionCause()); 
		}

		public SkinFetchFailedException(Reason reason, Exception exception) {
			super(reason.getExceptionCause(), exception); 
		}

		public static enum Reason {
			NO_PREMIUM_PLAYER("Can't find a valid premium player with that name"),
			NO_SKIN_DATA("No skin data found for player with that name"),
			SKIN_RECODE_FAILED("Can't decode skin data"),
			GENERIC_ERROR("An error has ouccured");

			private String exceptionCause;

			private Reason(String exceptionCause) {
				this.exceptionCause = exceptionCause;
			}

			public String getExceptionCause() {
				return exceptionCause;
			}
		}
		
	}

}
